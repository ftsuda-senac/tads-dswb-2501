package br.senac.tads.dsw.exemplo1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.MessageFormat;

@Controller
public class ExemploController {

    @GetMapping
    @ResponseBody
    public String exemplo1(@RequestHeader("user-agent") String userAgent,
                           @RequestParam(name = "nome", defaultValue = "Usuário") String nome) {
//        String htmlTemplate = """
//                <!doctype html>
//                <html>
//                    <head>
//                        <meta charset="UTF-8">
//                        <title>TADS DSW</title>
//                    </head>
//                    <body>
//                        <h1>Exemplo Spring Boot</h1>
//                        <h2>Olá {0}</h2>
//                        <p>User-agent: <code>{1}</code></p>
//                    </body>
//                </html>
//                """;
//        return MessageFormat.format(htmlTemplate.replace("'", "''"),
//                nome, userAgent);
        String jsonTemplate = """
                '{'
                   "nome": "{0}",
                   "userAgent": "{1}"
                '}'
                """;
        return MessageFormat.format(jsonTemplate.replace("'", "''"), nome, userAgent);
    }
}
