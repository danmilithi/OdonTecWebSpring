package br.com.odontec.controller;

import br.com.odontec.model.Agenda;
import br.com.odontec.service.AgendaService;
import br.com.odontec.service.DentistaService;
import br.com.odontec.service.PacienteService;
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
@RequestMapping("/agenda")
public class AgendaController {
    private final AgendaService service;
    private final PacienteService pacienteService;
    private final DentistaService dentistaService;
    private final TratamentoService tratamentoService;

    public AgendaController(
            AgendaService service,
            PacienteService pacienteService,
            DentistaService dentistaService,
            TratamentoService tratamentoService
    ) {
        this.service = service;
        this.pacienteService = pacienteService;
        this.dentistaService = dentistaService;
        this.tratamentoService = tratamentoService;
    }

    @GetMapping
    public String listar(@RequestParam(defaultValue = "") String busca, Model model) {
        prepararPagina(model, new Agenda(), busca);
        return "agenda";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Integer id, Model model) {
        prepararPagina(model, service.buscar(id), "");
        return "agenda";
    }

    @PostMapping("/salvar")
    public String salvar(
            @Valid @ModelAttribute("agenda") Agenda agenda,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors()) {
            prepararPagina(model, agenda, "");
            return "agenda";
        }
        service.salvar(agenda);
        redirectAttributes.addFlashAttribute("sucesso", "Consulta salva com sucesso.");
        return "redirect:/agenda";
    }

    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        service.excluir(id);
        redirectAttributes.addFlashAttribute("sucesso", "Consulta excluida com sucesso.");
        return "redirect:/agenda";
    }

    private void prepararPagina(Model model, Agenda agenda, String busca) {
        model.addAttribute("agenda", agenda);
        model.addAttribute("registros", service.listar(busca));
        model.addAttribute("pacientes", pacienteService.listar(""));
        model.addAttribute("dentistas", dentistaService.listar(""));
        model.addAttribute("tratamentos", tratamentoService.listar(""));
        model.addAttribute("busca", busca);
    }
}
