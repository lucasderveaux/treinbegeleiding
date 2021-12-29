package be.ugent.systemdesign.treinbegeleiding.application.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SecurailHelpCommand {
    private String station;
    private Integer trainId;
}
