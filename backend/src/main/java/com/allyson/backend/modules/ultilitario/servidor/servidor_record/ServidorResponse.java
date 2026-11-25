package com.allyson.backend.modules.ultilitario.servidor.servidor_record;

import java.time.LocalDateTime;

public record ServidorResponse(
        String status,
        LocalDateTime dataHora,
        String sistemaOperacional,
        String processador,
        int nucleosFisicos,
        int nucleosLogicos,
        long ramTotalGB,
        long ramDisponivelGB
) {}
