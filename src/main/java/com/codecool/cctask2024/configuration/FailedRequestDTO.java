package com.codecool.cctask2024.configuration;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FailedRequestDTO {

    String message;
    String path;
    String timestamp;
    String requestId;
    String contact;
}
