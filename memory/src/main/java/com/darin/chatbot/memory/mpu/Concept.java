package com.darin.chatbot.memory.mpu;

import lombok.Data;

@Data
class Concept {
    private String concept;
    private int refCount;
}
