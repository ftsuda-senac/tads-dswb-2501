package br.senac.tads.dsw.exemplo.rest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/pessoas/imagens")
public class UploadController {

    @PostMapping("/upload")
    public ResponseEntity<?> receberArquivo(@RequestParam MultipartFile arquivo) {
        Path destino = Path.of("C:/tads/dswb/uploads-server", arquivo.getOriginalFilename());
        try {
            Files.copy(arquivo.getInputStream(), destino, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            ex.printStackTrace();
            return ResponseEntity.unprocessableEntity().body("Erro - Arquivo já existe");
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/upload2")
    public ResponseEntity<?> receberArquivoComDados(@ModelAttribute DadosReceber dados) {
        System.out.println("Dados recebidos: " + dados.getTitulo() + ", " + dados.getLegenda());
        Path destino = Path.of("C:/tads/dswb/uploads-server", dados.getArquivo().getOriginalFilename());
        try {
            Files.copy(dados.getArquivo().getInputStream(), destino, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            ex.printStackTrace();
            return ResponseEntity.unprocessableEntity().body("Erro - Arquivo já existe");
        }
        return ResponseEntity.ok().build();
    }

    public class DadosReceber {

        private String titulo;

        private String legenda;

        private MultipartFile arquivo;

        public String getTitulo() {
            return titulo;
        }

        public void setTitulo(String titulo) {
            this.titulo = titulo;
        }

        public String getLegenda() {
            return legenda;
        }

        public void setLegenda(String legenda) {
            this.legenda = legenda;
        }

        public MultipartFile getArquivo() {
            return arquivo;
        }

        public void setArquivo(MultipartFile arquivo) {
            this.arquivo = arquivo;
        }


    }
}
