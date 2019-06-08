package dev.thedutchruben.core.framework.server;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Log {
    private Date date;
    private boolean isPublic;
    private String event;
}
