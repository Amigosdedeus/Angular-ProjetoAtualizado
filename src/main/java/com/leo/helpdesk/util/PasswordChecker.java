package com.leo.helpdesk.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordChecker {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String senhaFornecida = "123"; // A senha que você quer verificar
        String hashArmazenado = "$2a$10$g/D6XCq1Gpr8snL79l.ZTuS089YLIibbHF9ZHmEgBzLwCsaV5UYfC"; // Hash que está no seu banco de dados

        boolean matches = encoder.matches(senhaFornecida, hashArmazenado);

        if (matches) {
            System.out.println("A senha está correta.");
        } else {
            System.out.println("A senha está incorreta.");
        }
    }
}

