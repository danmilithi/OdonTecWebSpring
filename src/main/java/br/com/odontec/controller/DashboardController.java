package br.com.odontec.controller;

import br.com.odontec.service.AgendaService;
import br.com.odontec.service.DentistaService;
import br.com.odontec.service.FornecedorService;
import br.com.odontec.service.PacienteService;
import br.com.odontec.service.ProdutoService;
import br.com.odontec.service.TratamentoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
    private final PacienteService pacienteService;
    private final DentistaService dentistaService;
    private final FornecedorService fornecedorService;
    private final ProdutoService produtoService;
    private final TratamentoService tratamentoService;
    private final AgendaService agendaService;

    public DashboardController(
            PacienteService pacienteService,
            DentistaService dentistaService,
            FornecedorService fornecedorService,
            ProdutoService produtoService,
            TratamentoService tratamentoService,
            AgendaService agendaService
    ) {
        this.pacienteService = pacienteService;
        this.dentistaService = dentistaService;
        this.fornecedorService = fornecedorService;
        this.produtoService = produtoService;
        this.tratamentoService = tratamentoService;
        this.agendaService = agendaService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("totalPacientes", pacienteService.contar());
        model.addAttribute("totalDentistas", dentistaService.contar());
        model.addAttribute("totalFornecedores", fornecedorService.contar());
        model.addAttribute("totalProdutos", produtoService.contar());
        model.addAttribute("totalTratamentos", tratamentoService.contar());
        model.addAttribute("totalAgenda", agendaService.contar());
        return "dashboard";
    }
}
