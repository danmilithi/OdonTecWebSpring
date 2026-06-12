package br.com.odontec.controller;

import br.com.odontec.model.Dentista;
import br.com.odontec.service.DentistaService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/dentistas")
public class DentistaController {
    private final DentistaService service;

    public DentistaController(DentistaService service) {
        this.service = service;
    }

    @GetMapping
    public String listar(@RequestParam(defaultValue = "") String busca, Model model) {
        prepararPagina(model, new Dentista(), busca);
        return "dentistas";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Integer id, Model model) {
        prepararPagina(model, service.buscar(id), "");
        return "dentistas";
    }

    @PostMapping("/salvar")
    public String salvar(
            @Valid @ModelAttribute("dentista") Dentista dentista,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors()) {
            prepararPagina(model, dentista, "");
            return "dentistas";
        }
        service.salvar(dentista);
        redirectAttributes.addFlashAttribute("sucesso", "Dentista salvo com sucesso.");
        return "redirect:/dentistas";
    }

    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        service.excluir(id);
        redirectAttributes.addFlashAttribute("sucesso", "Dentista excluido com sucesso.");
        return "redirect:/dentistas";
    }

    private void prepararPagina(Model model, Dentista dentista, String busca) {
        model.addAttribute("dentista", dentista);
        model.addAttribute("registros", service.listar(busca));
        model.addAttribute("busca", busca);
    }
}
