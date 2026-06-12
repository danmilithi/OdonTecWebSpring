package br.com.odontec.controller;

import br.com.odontec.model.Usuario;
import br.com.odontec.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AutenticacaoController {
    private final UsuarioService usuarioService;

    public AutenticacaoController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping({"/", "/login"})
    public String login(HttpSession session) {
        if (session.getAttribute("usuarioLogado") != null) {
            return "redirect:/dashboard";
        }
        return "login";
    }

    @PostMapping("/login")
    public String autenticar(
            @RequestParam String login,
            @RequestParam String senha,
            HttpSession session,
            Model model
    ) {
        try {
            Usuario usuario = usuarioService.autenticar(login, senha).orElse(null);
            if (usuario == null) {
                model.addAttribute("erro", "Usuario ou senha invalidos.");
                model.addAttribute("login", login);
                return "login";
            }
            session.setAttribute("usuarioLogado", usuario);
            return "redirect:/dashboard";
        } catch (IllegalArgumentException ex) {
            model.addAttribute("erro", ex.getMessage());
            return "login";
        }
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
