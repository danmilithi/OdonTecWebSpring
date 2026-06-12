package br.com.odontec.controller;

import br.com.odontec.model.Produto;
import br.com.odontec.service.ProdutoService;
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
@RequestMapping("/produtos")
public class ProdutoController {
    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @GetMapping
    public String listar(@RequestParam(defaultValue = "") String busca, Model model) {
        prepararPagina(model, new Produto(), busca);
        return "produtos";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Integer id, Model model) {
        prepararPagina(model, service.buscar(id), "");
        return "produtos";
    }

    @PostMapping("/salvar")
    public String salvar(
            @Valid @ModelAttribute("produto") Produto produto,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors()) {
            prepararPagina(model, produto, "");
            return "produtos";
        }
        service.salvar(produto);
        redirectAttributes.addFlashAttribute("sucesso", "Produto salvo com sucesso.");
        return "redirect:/produtos";
    }

    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        service.excluir(id);
        redirectAttributes.addFlashAttribute("sucesso", "Produto excluido com sucesso.");
        return "redirect:/produtos";
    }

    private void prepararPagina(Model model, Produto produto, String busca) {
        model.addAttribute("produto", produto);
        model.addAttribute("registros", service.listar(busca));
        model.addAttribute("busca", busca);
    }
}
