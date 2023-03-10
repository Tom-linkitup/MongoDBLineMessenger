package com.example.mongodblinemessenger.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "line-message")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LineMessage {

    @Id
    private String id;
    private String userId;
    private String messageId;
    private String message;

}
