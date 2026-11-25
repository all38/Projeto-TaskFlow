package com.allyson.backend.modules.ultilitario.servidor.servidor_controller;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.allyson.backend.modules.ultilitario.servidor.servidor_record.ServidorResponse;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OperatingSystem;

@RestController
@RequestMapping("/servidor")
public class ServidorController {
    // ServidorResponse
    @GetMapping("/estado")
    public ServidorResponse verificarServidor() {
        SystemInfo informacoesSistema = new SystemInfo();
        HardwareAbstractionLayer hardware = informacoesSistema.getHardware();
        OperatingSystem so = informacoesSistema.getOperatingSystem();
        CentralProcessor processador = hardware.getProcessor();
        GlobalMemory memoria = hardware.getMemory();

        long gb = 1024L * 1024 * 1024;

        return new ServidorResponse(
                "ONLINE",
                LocalDateTime.now(),
                so.toString(),
                processador.getProcessorIdentifier().getName(),
                processador.getPhysicalProcessorCount(),
                processador.getLogicalProcessorCount(),
                memoria.getTotal() / gb,
                memoria.getAvailable() / gb);
    }
}
