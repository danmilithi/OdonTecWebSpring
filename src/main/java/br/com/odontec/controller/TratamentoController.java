package br.com.odontec.controller;

import br.com.odontec.model.Tratamento;
import br.com.odontec.service.TratamentoService;
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
@RequestMapping("/tratamentos")
public class TratamentoController {
    private final TratamentoService service;

    public TratamentoController(TratamentoService service) {
        this.service = service;
    }

    @GetMapping
    public String listar(@RequestParam(defaultValue = "") String busca, Model model) {
        prepararPagina(model, new Tratamento(), busca);
        return "tratamentos";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Integer id, Model model) {
        prepararPagina(model, service.buscar(id), "");
        return "tratamentos";
    }

    @PostMapping("/salvar")
    public String salvar(
            @Valid @ModelAttribute("tratamento") Tratamento tratamento,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors()) {
            prepararPagina(model, tratamento, "");
            return "tratamentos";
        }
        service.salvar(tratamento);
        redirectAttributes.addFlashAttribute("sucesso", "Tratamento salvo com sucesso.");
        return "redirect:/tratamentos";
    }

    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        service.excluir(id);
        redirectAttributes.addFlashAttribute("sucesso", "Tratamento excluido com sucesso.");
        return "redirect:/tratamentos";
    }

    private void prepararPagina(Model model, Tratamento tratamento, String busca) {
        model.addAttribute("tratamento", tratamento);
        model.addAttribute("registros", service.listar(busca));
        model.addAttribute("busca", busca);
    }
}
