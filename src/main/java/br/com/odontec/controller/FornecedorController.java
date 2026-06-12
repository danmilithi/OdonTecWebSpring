package br.com.odontec.controller;

import br.com.odontec.model.Fornecedor;
import br.com.odontec.service.FornecedorService;
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
@RequestMapping("/fornecedores")
public class FornecedorController {
    private final FornecedorService service;

    public FornecedorController(FornecedorService service) {
        this.service = service;
    }

    @GetMapping
    public String listar(@RequestParam(defaultValue = "") String busca, Model model) {
        prepararPagina(model, new Fornecedor(), busca);
        return "fornecedores";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Integer id, Model model) {
        prepararPagina(model, service.buscar(id), "");
        return "fornecedores";
    }

    @PostMapping("/salvar")
    public String salvar(
            @Valid @ModelAttribute("fornecedor") Fornecedor fornecedor,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors()) {
            prepararPagina(model, fornecedor, "");
            return "fornecedores";
        }
        service.salvar(fornecedor);
        redirectAttributes.addFlashAttribute("sucesso", "Fornecedor salvo com sucesso.");
        return "redirect:/fornecedores";
    }

    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        service.excluir(id);
        redirectAttributes.addFlashAttribute("sucesso", "Fornecedor excluido com sucesso.");
        return "redirect:/fornecedores";
    }

    private void prepararPagina(Model model, Fornecedor fornecedor, String busca) {
        model.addAttribute("fornecedor", fornecedor);
        model.addAttribute("registros", service.listar(busca));
        model.addAttribute("busca", busca);
    }
}
