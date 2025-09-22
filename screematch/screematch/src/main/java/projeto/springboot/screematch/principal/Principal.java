package projeto.springboot.screematch.principal;

import projeto.springboot.screematch.model.DadosEpisodio;
import projeto.springboot.screematch.model.DadosSerie;
import projeto.springboot.screematch.model.DadosTemporada;
import projeto.springboot.screematch.model.Episodio;
import projeto.springboot.screematch.service.ConsumoApi;
import projeto.springboot.screematch.service.CoverteDados;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private final String ENDEREÇO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=f2bfc8e5";
    private ConsumoApi consumoApi = new ConsumoApi();
    private CoverteDados conversor = new CoverteDados();
    private Scanner sc = new Scanner(System.in);


    public void exibeMenu(){
        var menu = """
                1-Buscar series
                2-Buscar Episodios
                
                0-Sair
                """;
        System.out.println(menu);
        var opcao = sc.nextInt();
        sc.nextLine();


        System.out.println("Digite o nome da serie para busca:");
        var nomeSerie =  sc.nextLine();
        var json = consumoApi.obterdados(ENDEREÇO+nomeSerie.replace(" ","+")+API_KEY);
        DadosSerie dados = conversor.Obterdados(json, DadosSerie.class);
        System.out.println(dados);
        List<DadosTemporada> temporadas = new ArrayList<>();
        for(int i= 1;i<=dados.totaltemporadas();i++){
        	json = consumoApi.obterdados(ENDEREÇO+nomeSerie.replace(" ","+")+"&season="+i+API_KEY);
        	DadosTemporada dadosTemporada = conversor.Obterdados(json,DadosTemporada.class);
        	temporadas.add(dadosTemporada);
        }
        temporadas.forEach(t -> t.episodios().forEach( e -> System.out.println(" "+e.Titulo())) );
//    List<DadosEpisodio> dadosEpisodios = temporadas.stream()
//            .flatMap(t -> t.episodios().stream()).
//            collect(Collectors.toList());
//    dadosEpisodios.stream().filter(e -> !e.Avaliação().equalsIgnoreCase("N/A")).sorted(Comparator.comparing(DadosEpisodio::Avaliação).reversed()).limit(5).forEach(System.out::println);
    List<Episodio> episodios = temporadas.stream()
            .flatMap(t->t.episodios().stream()
                    .map(d -> new Episodio(t.Numero(), d ))
            ).collect(Collectors.toList());
    episodios.stream().sorted(Comparator.comparing(Episodio::getAvaliação).reversed()).limit(5).forEach(System.out::println);

//    System.out.println("Deseja ver os episodios de qual temporada?");
//    var temp = sc.nextInt();
//    sc.nextLine();
//    episodios.stream().filter(e -> e.getTemporada() == temp)
//            .forEach(e-> System.out.println("Temporada "+e.getTemporada()+" Episodio: "+e.getNumero()+" "+e.getTitulo()));
//
 System.out.println("Digite um trecho do Titulo do episodio");
var trecho = sc.nextLine();
 Optional<Episodio> episodioBuscado = episodios.stream()
         .filter(e -> e.getTitulo().toUpperCase().contains(trecho.toUpperCase()))
               .findFirst();
 if(episodioBuscado.isPresent()){
            System.out.println("Episodio Encontrado");
           System.out.println("Episodio: "+episodioBuscado.get().getTitulo());
       }else{
            System.out.println("Episodio não encontrado");
        }
        Map<Integer,Double>avaliacoesPorTemporada = episodios.stream().
                filter(e-> e.getAvaliação()>0)
                .collect(Collectors.groupingBy(Episodio::getTemporada,
                        Collectors.averagingDouble(Episodio::getAvaliação)));
        System.out.println(avaliacoesPorTemporada);
        DoubleSummaryStatistics est = episodios.stream()
                .filter(e-> e.getAvaliação()>0)
                .collect(Collectors.summarizingDouble(Episodio::getAvaliação));
        System.out.println("Epsodios Avaliados: "+est.getCount());
        System.out.println("Media: "+est.getAverage());
        System.out.println("Nota maxima de um Epsodio: "+est.getMax());
        System.out.println("Pior nota de um Epsodio: "+est.getMin());
        
    }
    public void buscarSerieWeb(){
        System.out.println("Digite o nome da serie para busca:");
        var nomeSerie =  sc.nextLine();
        var json = consumoApi.obterdados(ENDEREÇO+nomeSerie.replace(" ","+")+API_KEY);
        DadosSerie dados = conversor.Obterdados(json, DadosSerie.class);
        System.out.println(dados);
        List<DadosTemporada> temporadas = new ArrayList<>();
        for(int i= 1;i<=dados.totaltemporadas();i++){
            json = consumoApi.obterdados(ENDEREÇO+nomeSerie.replace(" ","+")+"&season="+i+API_KEY);
            DadosTemporada dadosTemporada = conversor.Obterdados(json,DadosTemporada.class);
            temporadas.add(dadosTemporada);
        }
    }
    public void buscarEpsodioPorSerie(){
        System.out.println("Digite um trecho do Titulo do episodio");
        var trecho = sc.nextLine();
        Optional<Episodio> episodioBuscado = episodios.stream()
                .filter(e -> e.getTitulo().toUpperCase().contains(trecho.toUpperCase()))
                .findFirst();
        if(episodioBuscado.isPresent()){
            System.out.println("Episodio Encontrado");
            System.out.println("Episodio: "+episodioBuscado.get().getTitulo());
        }else{
            System.out.println("Episodio não encontrado");
        }
        Map<Integer,Double>avaliacoesPorTemporada = episodios.stream().
                filter(e-> e.getAvaliação()>0)
                .collect(Collectors.groupingBy(Episodio::getTemporada,
                        Collectors.averagingDouble(Episodio::getAvaliação)));
        System.out.println(avaliacoesPorTemporada);
        DoubleSummaryStatistics est = episodios.stream()
                .filter(e-> e.getAvaliação()>0)
                .collect(Collectors.summarizingDouble(Episodio::getAvaliação));
        System.out.println("Epsodios Avaliados: "+est.getCount());
        System.out.println("Media: "+est.getAverage());
        System.out.println("Nota maxima de um Epsodio: "+est.getMax());
        System.out.println("Pior nota de um Epsodio: "+est.getMin());

    }
    }
}
