package intrusii.web.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class ClientDto extends BaseDto {
    private IDCardDto idCard;
    private String name;
    private String email;
}
