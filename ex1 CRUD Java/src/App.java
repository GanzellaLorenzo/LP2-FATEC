import java.util.Scanner;
import entity.Servico;
import repository.ServicoRepository;

public class App {
    public static void main(String[] args) {
        try {
            ServicoRepository repository = new ServicoRepository();
            Scanner ler = new Scanner(System.in);
            int opcao;
            
            do {
                System.out.println("\n////// Menu de Serviços //////");
                System.out.println("1 - Cadastrar Serviço" +
                    "\n2 - Excluir Serviço" +
                    "\n3 - Editar Serviço" +
                    "\n4 - Listar Serviços" +
                    "\n5 - Pesquisar por Descrição" +
                    "\n10 - Sair");
                System.out.print("Opção: ");
                opcao = ler.nextInt();
                ler.nextLine();

                switch (opcao) {
                    case 1:
                        Servico novoServico = new Servico();
                        System.out.print("Descrição: ");
                        novoServico.setDescricao(ler.nextLine());
                        System.out.print("Valor: ");
                        novoServico.setValor(ler.nextFloat());
                        ler.nextLine();
                        
                        if(repository.incluir(novoServico)) {
                            System.out.println("Serviço cadastrado!");
                        } else {
                            System.out.println("Erro ao cadastrar!");
                        }
                        break;
                        
                    case 2:
                        System.out.print("ID para excluir: ");
                        int idExcluir = ler.nextInt();
                        ler.nextLine();
                        
                        if(repository.excluir(idExcluir)) {
                            System.out.println("Serviço excluído!");
                        } else {
                            System.out.println("Erro na exclusão!");
                        }
                        break;
                        
                    case 3:
                        System.out.print("ID para editar: ");
                        int idEditar = ler.nextInt();
                        ler.nextLine();
                        
                        Servico servicoEditar = repository.obter(idEditar);
                        if(servicoEditar != null) {
                            System.out.print("Nova descrição (" + servicoEditar.getDescricao() + "): ");
                            String novaDesc = ler.nextLine();
                            if(!novaDesc.isEmpty()) servicoEditar.setDescricao(novaDesc);
                            
                            System.out.print("Novo valor (" + servicoEditar.getValor() + "): ");
                            float novoValor = ler.nextFloat();
                            ler.nextLine();
                            servicoEditar.setValor(novoValor);
                            
                            if(repository.editar(servicoEditar)) {
                                System.out.println("Serviço atualizado!");
                            }
                        } else {
                            System.out.println("Serviço não encontrado!");
                        }
                        break;
                        
                    case 4:
                        System.out.println("\n//// Lista de Serviços ////");
                        for(Servico s : repository.listar()) {
                            System.out.println("ID: " + s.getId() + 
                                " | Descrição: " + s.getDescricao() + 
                                " | Valor: R$" + String.format("%.2f", s.getValor()));
                        }
                        break;
                        
                    case 5:
                        System.out.print("Termo de pesquisa: ");
                        String termo = ler.nextLine();
                        System.out.println("\n /// Resultados ///");
                        for(Servico s : repository.pesquisar(termo)) {
                            System.out.println("ID: " + s.getId() + 
                                " | Descrição: " + s.getDescricao() + 
                                " | Valor: R$" + String.format("%.2f", s.getValor()));
                        }
                        break;
                }
            } while(opcao != 10);
            
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}