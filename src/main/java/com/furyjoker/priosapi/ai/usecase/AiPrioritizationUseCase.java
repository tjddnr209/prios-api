package com.furyjoker.priosapi.ai.usecase;

import com.furyjoker.priosapi.orderinfo.service.OrderInfoService;
import com.furyjoker.priosapi.schedule.domain.Schedule;
import com.furyjoker.priosapi.schedule.infrastructure.ScheduleRepository;
import com.furyjoker.priosapi.todo.domain.Todo;
import com.furyjoker.priosapi.todo.infrastructure.TodoRepository;
import com.furyjoker.priosapi.ai.client.ClaudeClient;
import com.furyjoker.priosapi.global.exception.PriosException;
import com.furyjoker.priosapi.global.exception.type.OrderInfoErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AiPrioritizationUseCase {

    private final TodoRepository todoRepository;
    private final ScheduleRepository scheduleRepository;
    private final OrderInfoService orderInfoService;
    private final ClaudeClient claudeClient;

    public void prioritize(Long memberId) {
        // 1. 할 일과 일정 데이터 가져오기
        List<Todo> todos = todoRepository.findByMemberId(memberId);
        List<Schedule> schedules = scheduleRepository.findByMemberId(memberId);

        // 2. AI에 전달할 프롬프트 생성 (아이젠하워 매트릭스 기준)
        String prompt = generatePrompt(todos, schedules);

        // 3. Claude에게 우선순위 요청 (API 호출)
        List<Long> sortedIds = claudeClient.getPrioritizedList(prompt);

        // 4. 결과로 받은 ID 순서대로 OrderInfo 업데이트
        try {
            orderInfoService.updateOrder(memberId, sortedIds);
        } catch (Exception e) {
            throw new PriosException(OrderInfoErrorCode.ORDER_UPDATE_FAILED);
        }
    }

    private String generatePrompt(List<Todo> todos, List<Schedule> schedules) {
        StringBuilder prompt = new StringBuilder();
        prompt.append("아래는 사용자의 할 일과 일정 목록입니다.\n");
        prompt.append("각 항목에 대해 아이젠하워 매트릭스를 기준으로 중요도와 긴급도를 판단하고, 우선순위 순서대로 ID만 출력해주세요.\n");
        prompt.append("항목 목록:\n");

        todos.forEach(todo ->
                prompt.append("{ \"id\": ").append(todo.getId())
                        .append(", \"title\": \"").append(todo.getTitle())
                        .append("\", \"description\": \"").append(todo.getDescription())
                        .append("\", \"type\": \"TODO\" },\n")
        );

        schedules.forEach(schedule ->
                prompt.append("{ \"id\": ").append(schedule.getId())
                        .append(", \"title\": \"").append(schedule.getTitle())
                        .append("\", \"description\": \"").append(schedule.getDescription())
                        .append("\", \"type\": \"SCHEDULE\" },\n")
        );

        return prompt.toString();
    }
}
