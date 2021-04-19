package intrusii.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SubscriptionsDto {
    private Set<SubscriptionDto> subscriptions;
}
