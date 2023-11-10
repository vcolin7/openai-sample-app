package org.example;

import com.generic.json.JsonSerializable;
import com.generic.json.JsonWriter;

import java.io.IOException;
import java.util.List;

public class ChatCompletionParameters implements JsonSerializable<ChatCompletionParameters> {
    private final String model;
    private final List<Message> messages;

    public ChatCompletionParameters(String model, List<Message> messages) {
        this.model = model;
        this.messages = messages;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("model", model);
        jsonWriter.writeArrayField("messages", messages, (writer, message) -> {
            writer.writeStartObject();
            writer.writeStringField("role", message.getRole());
            writer.writeStringField("content", message.getContent());
            writer.writeEndObject();
        });

        return jsonWriter.writeEndObject();
    }

    public static class Message {
        private final String role;
        private final String content;

        public Message(String role, String content) {
            this.role = role;
            this.content = content;
        }

        public String getRole() {
            return role;
        }

        public String getContent() {
            return content;
        }
    }
}
