import java.util.Scanner;
import javax.swing.*;
/**
 * @author Fernando Nunes
 * date: 15-11-2017  18:44
 * phone: (+244)923425404 
 * **/
public class ConversaoArabeRomano {	
	/**Variáveis de classe (globais)*/
	static Scanner input = new Scanner(System.in);
	static final String MSG = "#ERRO\nOperacao invalida!";
	/**
	 * Método que verifica se um dado número árabe pode ser convertido para numeração romana
	 * Os números romanos aceites variam de 1 a 3999, inclusive
	 * Tipo de retorno: boolean
	 */
	static boolean isValido(int numero){
		if(numero > 0 && numero < 4000)
			return true;
		return false;
	}
	/**
	 * Método que verifica se a string recebida é um número romano válido
	 * O método verifica se os caracteres são romanos e se a sequencia é válida
	 * Tipo de retorno: boolean
	 */
	static boolean isValido(String romano){
		char 	caracteresValidos [] = {'M','D','C','L','X','V','I'}; //Vector com os caracteres romanos válidos
		String sequenciaInvalida4 [] = {"MMMM","CCCC","XXXX","IIII"}; //Vector com sequência de 4 caracteres inválidos
		String sequenciaInvalida3 [] = {"DDD","LLL","VVV","CCM","CCD","XXC","XXL","IIX","IIV"}; //Vector com sequência de 3 caracteres inválidos
		String sequenciaInvalida2 [] = {"DD","LL","VV","DM","LM","XM","VM","LD","XD","VD","ID","LC","VC","IC","VL","IL","VX"}; //Vector com sequência de 2 caracteres inválidos
		
		boolean encontrado, seq_invalida; String romano_auxiliar;
		int contador_I = 0 , contador_X = 0 , contador_C = 0 , contador_M = 0;
		 
		/**Estrutura que procura o caracter válido... se não encontrar, retorna FALSE*/
		for(int i = 0 ; i < romano.length() ; i++){
			encontrado = false;
			for(int k = 0 ; k < caracteresValidos.length ; k++){
				if(romano.charAt(i) == caracteresValidos[k]){
					encontrado = true;
					break;
				}
			}
			if(!(encontrado))
				return false;	
		}
		
		/**Estrutura que procura sequência de "dois" caracteres inválida... 
		 * se encontrar, retorna FALSE*/
		if(romano.length() >= 2){
			for(int i = 0 ; i+1 < romano.length() ; i++){
				seq_invalida = false;
				romano_auxiliar = romano.substring(i,i+2);
				
				for(int k = 0 ; k < sequenciaInvalida2.length ; k++){
					if(romano_auxiliar.equals(sequenciaInvalida2[k])){
						seq_invalida = true;
						break;
					}
				}	
				if(seq_invalida)
					return false;
			}
		}
		
		/**Estrutura que procura sequência de "três" caracteres inválida... 
		 * se encontrar, retorna FALSE*/
		if(romano.length() >= 3){
			for(int i = 0 ; i+2 < romano.length() ; i++){
				seq_invalida = false;
				romano_auxiliar = romano.substring(i,i+3);
				
				for(int k = 0 ; k < sequenciaInvalida3.length ; k++){
					if(romano_auxiliar.equals(sequenciaInvalida3[k])){
						seq_invalida = true;
						break;
					}
				}
				if(seq_invalida)
					return false;
			}
		}
		
		/**Estrutura que procura sequência de "quatro" caracteres inválida... 
		 * se encontrar, retorna FALSE*/
		if(romano.length() >= 4){
			for(int i=0 ; i+3<romano.length() ; i++){
				seq_invalida = false;
				romano_auxiliar = romano.substring(i,i+4);
				for(int k=0 ; k<sequenciaInvalida4.length ; k++){
					if(romano_auxiliar.equals(sequenciaInvalida4[k])){
						seq_invalida = true;
						break;
					}
				}		
				if(seq_invalida)
					return false;
			}
			
			/**Estrutura que procura as quantidades de I, X, C e M na sequência... 
			 * se encontrar as quantidades acima das permitidas, retorna FALSE*/
			for(int i = 0 ; i < romano.length() ; i++){
				if(romano.charAt(i)=='M') contador_M++;
				if(romano.charAt(i)=='C') contador_C++;
				if(romano.charAt(i)=='X') contador_X++;
				if(romano.charAt(i)=='I') contador_I++;
			}
			if(contador_M > 4 || contador_C > 4 || contador_X > 4 || contador_I > 3)
				return false;
		}
		return true;
	} /**Fim do método "isValido"*/
	
	/**Método que converte a string (número romano) para árabe... 
	 * Retorno: um valor inteiro (que corresponde ao valor árabe)*/
	static int converter(String romano){
		int arabe = 0 , k;
		/**Estrutura que percorre a string e soma os valores árabes correspondentes*/
		for(int i = 0 ; i < romano.length() ; i++){
			if(i == 0)
				k = i;
			else
				k = i-1;
			
			/**Se o caracter é M e não tiver C antes de M, incrementa 1000*/
			if(romano.charAt(i)=='M' && romano.charAt(k)!='C')
				arabe = arabe + 1000;
			
			/**Se o caracter é M e tiver C antes de M, incrementa 800 (pois já terá incrementado 100)*/
			if(romano.charAt(i)=='M' && romano.charAt(k)=='C')
				arabe = arabe + 800;
			
			/**Se o caracter é D e não tiver C antes de D, incrementa 500*/
			if(romano.charAt(i)=='D' && romano.charAt(k)!='C')
				arabe = arabe + 500;
			
			/**Se o caracter é D e tiver C antes de D, incrementa 300 (pois já terá incrementado 100)*/
			if(romano.charAt(i)=='D' && romano.charAt(k)=='C')
				arabe = arabe + 300;
			
			/**Se o caracter é C e não tiver X antes de C, incrementa 100*/
			if(romano.charAt(i)=='C' && romano.charAt(k)!='X')
				arabe = arabe + 100;
			
			/**Se o caracter é C e tiver X antes de C, incrementa 80 (pois já terá incrementado 10)*/
			if(romano.charAt(i)=='C' && romano.charAt(k)=='X')
				arabe = arabe + 80;
			
			/**Se o caracter é L e não tiver X antes de L, incrementa 50*/
			if(romano.charAt(i)=='L' && romano.charAt(k)!='X')
				arabe = arabe + 50;
			
			/**Se o caracter é L e tiver X antes de L, incrementa 30 (pois já terá incrementado 10)*/
			if(romano.charAt(i)=='L' && romano.charAt(k)=='X')
				arabe = arabe + 30;
			
			/**Se o caracter é X e não tiver I antes de X, incrementa 10*/
			if(romano.charAt(i)=='X' && romano.charAt(k)!='I')
				arabe = arabe + 10;
			
			/**Se o caracter é X e tiver I antes de X, incrementa 8 (pois já terá incrementado 1)*/
			if(romano.charAt(i)=='X' && romano.charAt(k)=='I')
				arabe = arabe + 8;
			
			/**Se o caracter é V e não tiver I antes de V, incrementa 5*/
			if(romano.charAt(i)=='V' && romano.charAt(k)!='I')
				arabe = arabe + 5;
			
			/**Se o caracter é V e tiver I antes de V, incrementa 3 (pois já terá incrementado 1)*/
			if(romano.charAt(i)=='V' && romano.charAt(k)=='I')
				arabe = arabe + 3;
			
			/**Se o caracter é I, incrementa 1*/
			if(romano.charAt(i)=='I')
				arabe ++;
		}		
		return arabe;		
	} /**Fim do método que converte romano para árabe*/
	
	/**Método que converte a string (número romano) para árabe... 
	 * Retorno: um valor inteiro (que corresponde ao valor árabe)*/
	static String converter(int arabe){
		StringBuilder romano = new StringBuilder();
		final int SENTINELA = -1;
		
		while(arabe > 0){
			/**Enquanto dá pra retirar 1000, decrementa 1000 em arabe e "acrescenta" M em romano*/
			while(arabe - 1000 > SENTINELA){
				arabe = arabe - 1000;
				romano.append("M");
			}
			
			/**Enquanto dá pra retirar 900, decrementa 900 em arabe e acrescenta "CM" em romano*/
			while(arabe - 900 > SENTINELA){
				arabe = arabe - 900;
				romano.append("CM");
			}
			
			/**Enquanto dá pra retirar 500, decrementa 500 em arabe e acrescenta "D" em romano*/
			while(arabe - 500 > SENTINELA){
				arabe = arabe - 500;
				romano.append("D");
			}
				
			/**Enquanto dá pra retirar 400, decrementa 400 em arabe e acrescenta "CD" em romano*/
			while(arabe - 400 > SENTINELA){
				arabe = arabe - 400;
				romano.append("CD");
			}
			
			/**Enquanto dá pra retirar 100, decrementa 100 em arabe e acrescenta "C" em romano*/
			while(arabe - 100 > SENTINELA){
				arabe = arabe - 100;
				romano.append("C");
			}
				
			/**Enquanto dá pra retirar 90, decrementa 90 em arabe e acrescenta "XC" em romano*/
			while(arabe - 90 > SENTINELA){
				arabe = arabe - 90;
				romano.append("XC");
			}
				
			/**Enquanto dá pra retirar 50, decrementa 50 em arabe e acrescenta "L" em romano*/
			while(arabe - 50 > SENTINELA){
				arabe = arabe - 50;
				romano.append("L");
			}
				
			/**Enquanto dá pra retirar 40, decrementa 40 em arabe e acrescenta "XL" em romano*/
			while(arabe - 40 > SENTINELA){
				arabe = arabe - 40;
				romano.append("XL");
			}
				
			/**Enquanto dá pra retirar 10, decrementa 10 em arabe e acrescenta "X" em romano*/
			while(arabe - 10 > SENTINELA){
				arabe = arabe - 10;
				romano.append("X");
			}
				
			/**Enquanto dá pra retirar 9, decrementa 9 em arabe e acrescenta "IX" em romano*/
			while(arabe - 9 > SENTINELA){
				arabe = arabe - 9;
				romano.append("IX");
			}
				
			/**Enquanto dá pra retirar 5, decrementa 5 em arabe e acrescenta "V" em romano*/
			while(arabe - 5 > SENTINELA){
				arabe = arabe - 5;
				romano.append("V");
			}
				
			/**Enquanto dá pra retirar 4, decrementa 4 em arabe e acrescenta "IV" em romano*/
			while(arabe - 4 > SENTINELA){
				arabe = arabe - 4;
				romano.append("IV");
			}
				
			/**Enquanto dá pra retirar 1, decrementa 1 em arabe e acrescenta "I" em romano*/
			while(arabe - 1 > SENTINELA){
				arabe = arabe - 1;
				romano.append("I");
			}
		}	
		String romano_auxiliar = new String(romano); //Converte a StringBuilder em String
		return romano_auxiliar;
	} /**Fim do método que converte árabe para romano*/
	
	/**Método que efectua e retorna a soma de dois valores romanos... 
	 * Retorno: a string ou "FALHA" (caso um dos valores romanos for inválido)*/
	static String somar(String romano1, String romano2){
		int aux_rom1 , aux_rom2 , soma ; String resultado , texto = MSG;
		
		if(isValido(romano1) && isValido(romano2)){
			aux_rom1 = converter(romano1); 
			aux_rom2 = converter(romano2);
			soma = aux_rom1 + aux_rom2;
			
			if(isValido(soma)){
				resultado = converter(soma);
				texto = romano1+" + "+romano2+" = "+resultado;
			}
		}	
		return texto;
	} /**Fim do método que efectua a soma*/
	
	/**Método que efectua e retorna a diferença entre dois valores romanos... 
	 * Retorno: a string ou "FALHA" (caso um dos valores romanos for inválido)*/
	static String subtrair(String romano1, String romano2){
		int aux_rom1 , aux_rom2 , diferenca ; String resultado , texto = MSG;
		
		if(isValido(romano1) && isValido(romano2)){
			aux_rom1 = converter(romano1); 
			aux_rom2 = converter(romano2);
			diferenca = aux_rom1 - aux_rom2;
			
			if(isValido(diferenca)){
				resultado = converter(diferenca);
				texto = romano1+" - "+romano2+" = "+resultado;
			}
		}
		return texto;
	}/**Fim do método que efectua a subtracção*/
	
	/**Método que efectua e retorna o quociente entre dois valores romanos... 
	 * Retorno: a string ou "FALHA" (caso um dos valores romanos for inválido)*/
	static String dividir(String rom1, String rom2){
		int aux_rom1 , aux_rom2 , quociente ; String resultado , texto = MSG;
		
		if(isValido(rom1) && isValido(rom2)){
			aux_rom1 = converter(rom1); 
			aux_rom2 = converter(rom2);
			quociente = aux_rom1 / aux_rom2;
			
			if(isValido(quociente)){
				resultado = converter(quociente);
				texto = rom1+" : "+rom2+" = "+resultado;
			}
		}
		return texto;
	}/**Fim do método que efectua a divisão*/
	
	/**Método que efectua e retorna o produto de dois valores romanos... 
	 * Retorno: a string ou "FALHA" (caso um dos valores romanos for inválido)*/
	static String multiplicar(String romano1, String romano2){
		int aux_rom1 , aux_rom2 , produto ; String resultado , texto = MSG;
		
		if(isValido(romano1) && isValido(romano2)){
			aux_rom1 = converter(romano1); 
			aux_rom2 = converter(romano2);
			produto = aux_rom1 * aux_rom2;
			
			if(isValido(produto)){
				resultado = converter(produto);
				texto = romano1+" x "+romano2+" = "+resultado;
			}
		}
		return texto;
	} /**Fim do método que efectua a multiplicação*/
	
	/**Método sem retorno que gere as opções (operações) sobre os números*/
	static void menu(){
		int sentinela , numero ; String romano1="", romano2="";	
		do{
			String opcao = JOptionPane.showInputDialog("\n ==PROGRAMA DE CONVERSAO== \n" +
													"\n 1 - Converter Arabe to Romano"+
													"\n 2 - Converter Romano to Arabe"+
													"\n 3 - Adicao de Romanos" +
													"\n 4 - Subtraccao de Romanos" +
													"\n 5 - Multiplicacao de Romanos" +
													"\n 6 - Divisao de Romanos" +
													"\n 7 - Terminar o programa..." +
													"\n\n Opcao ");
			
			sentinela = Integer.parseInt(opcao);
		
			switch(sentinela){
				case 1: numero = Integer.parseInt(JOptionPane.showInputDialog("Digite o valor arabe"));
					
						if(isValido(numero))
							JOptionPane.showMessageDialog(null,numero+" = "+converter(numero));
						else
							JOptionPane.showMessageDialog(null, "#ERRO\nImpossivel converter!", 
														  null, JOptionPane.ERROR_MESSAGE);break;
							
				case 2: romano1 = JOptionPane.showInputDialog("Digite um valor romano");
						romano1 = romano1.toUpperCase();
						
						if(isValido(romano1))
							JOptionPane.showMessageDialog(null,romano1+" = "+converter(romano1));
						else
							JOptionPane.showMessageDialog(null, "#ERRO\nImpossivel converter!", 
														  null, JOptionPane.ERROR_MESSAGE);break;
							
				case 3: romano1 = JOptionPane.showInputDialog("Digite o primeiro valor romano");
						romano2 = JOptionPane.showInputDialog("Digite o segundo valor romano");
						JOptionPane.showMessageDialog(null,somar(romano1.toUpperCase(), romano2.toUpperCase()));
						break;
						
				case 4: romano1 = JOptionPane.showInputDialog("Digite o primeiro valor romano");
						romano2 = JOptionPane.showInputDialog("Digite o segundo valor romano");
						JOptionPane.showMessageDialog(null,subtrair(romano1.toUpperCase(), romano2.toUpperCase()));
						break;
						
				case 5: romano1 = JOptionPane.showInputDialog("Digite o primeiro valor romano");
						romano2 = JOptionPane.showInputDialog("Digite o segundo valor romano");
						JOptionPane.showMessageDialog(null,multiplicar(romano1.toUpperCase(), romano2.toUpperCase()));
						break;
						
				case 6: romano1 = JOptionPane.showInputDialog("Digite o primeiro valor romano");
						romano2 = JOptionPane.showInputDialog("Digite o segundo valor romano");
						JOptionPane.showMessageDialog(null,dividir(romano1.toUpperCase(), romano2.toUpperCase()));
						break;
						
				case 7: System.exit(0); break;
		
				default: JOptionPane.showMessageDialog(null, "#ERRO\nOpcao Invalida!", 
													   null, JOptionPane.ERROR_MESSAGE);break;		
			}
		}while(sentinela != 7);	
	}
	
	/**Método principal... controla as operações com o menu (IDE)*/
	public static void main(String[] args) {
		menu(); //Chamada do método que contém o menu
		
		System.exit(0);
	}
} //Fim da classe