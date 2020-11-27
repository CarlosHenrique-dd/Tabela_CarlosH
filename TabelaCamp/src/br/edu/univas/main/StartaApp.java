package br.edu.univas.main;

import java.util.Scanner;

import br.edu.univas.vo.Jogos;
import br.edu.univas.vo.Times;

public class StartaApp {
	static Scanner sc = new Scanner(System.in);
	static int contTimes,contJogos;
	static Times[] teams = new Times[50];
	static Jogos[] games = new Jogos[50];
	static int[]gols = new int[50];
	static int[]saldoG = new int[50];
	static int[]pontos = new int[50];
	
	public static void main(String[]args) {
		int menu = -1;
		int escolha, golsM = -1, golsV = -1;
		do {
			orgTabela();
			Times times = new Times();
			System.out.println("1.Cadastrar 2.Editar 3.Excluir 4.Cadastrar Jogo 5.Editar Jogo 6.Excluir 7.Classificaçao");
			menu = sc.nextInt();
			Jogos jogos = new Jogos();
			
			if(menu == 1) {
				System.out.println("Qual o nome do Time ?");
				times.nome = sc.next();
				System.out.println("Qual o estado do time ?");
				times.estado = sc.next();
				teams[contTimes] = times;
				contTimes++;}
			
			if(menu == 2) {
				imprimeTime();
				System.out.println("Qual o numero do time que deseja Editar ?");
				escolha = sc.nextInt();
				System.out.println("Qual o nome do Time ?");
				teams[escolha-1].nome = sc.next();
				System.out.println("Qual o estado do Time ?");
				teams[escolha-1].estado = sc.next();
				escolha=-1;}
			
			if(menu == 3) {
				imprimeTime();
				System.out.println("Qual o numero do time deseja Excluir ?");
				escolha = sc.nextInt();
				teams[escolha-1].nome = null;
				teams[escolha-1].estado = null;
				pontos[escolha-1]=0;
				saldoG[escolha-1]=0;
				for(int i=escolha-1;i<contTimes-1;i++) {	
				teams[i] = teams[i+1];
				pontos[i] = pontos[i+1];
				saldoG[i] = saldoG[i+1];}
				contTimes = contTimes-1;
				escolha=-1;}
			
			if(menu == 4) {
				
				
				imprimeTime();
				System.out.println("Qual o numero do mandante ?");
				jogos.timeMand = sc.nextInt();
				System.out.println("Quantos gols o mandante fez ?");
				golsM = sc.nextInt();
				jogos.GolsMand = golsM;
				gols[jogos.timeMand]=golsM;
				System.out.println("Qual o numero do visitante ?");
				jogos.timeVisi = sc.nextInt();
				System.out.println("Quantos gols o visitante fez ?");
				golsV = sc.nextInt();
				jogos.golsVisi = golsV;
				gols[jogos.timeVisi]=golsV;
				games[contJogos] = jogos;
				
				if(golsM == golsV) {
					pontos[jogos.timeMand-1] = pontos[jogos.timeMand-1] + 1;
					pontos[jogos.timeVisi-1] = pontos[jogos.timeVisi-1] + 1;}
				
				else if(golsM > golsV){
					saldoG[jogos.timeMand-1] = saldoG[jogos.timeMand-1] + (golsM-golsV);
					saldoG[jogos.timeVisi-1] = saldoG[jogos.timeVisi-1] + (golsV-golsM);
					pontos[jogos.timeMand-1] = pontos[jogos.timeMand-1] + 3;}
				else{
					saldoG[jogos.timeVisi-1] = saldoG[jogos.timeVisi-1] + (golsV-golsM);
					saldoG[jogos.timeMand-1] = saldoG[jogos.timeMand-1] + (golsM-golsV);
					pontos[jogos.timeVisi-1] = pontos[jogos.timeVisi-1] + 3;}
				contJogos++;}
		
			if(menu == 5) {
				imprimeJogos();
				System.out.println("Qual jogos deseja Editar ?");
				escolha = sc.nextInt();
				System.out.println("Quantos gols o time mandante fez ?");
				games[escolha-1].GolsMand = sc.nextInt();
				saldoG[games[escolha-1].timeMand] = games[escolha-1].GolsMand;
				System.out.println("Quantos gols o visitante fez ?");
				games[escolha-1].golsVisi = sc.nextInt();
				saldoG[games[escolha-1].timeVisi] = games[escolha-1].golsVisi;
				
				escolha=-1;}
			
			if(menu == 6) {
				imprimeJogos();
				System.out.println("Qual jogo deseja Excluir?");
				escolha = sc.nextInt();
				
				games[escolha-1].timeMand = -1;
				games[escolha-1].timeVisi = -1;
				games[escolha-1].GolsMand = 0;
				games[escolha-1].golsVisi = 0;
				
				for(int i=escolha-1;i<contJogos;i++) {
					games[i] = games[i+1];}contJogos--;}
			
			if(menu == 7) {
				for(int i=0;i<contTimes;i++) {
					System.out.println(i+1+"."+teams[i].nome+" "+pontos[i]+" "+saldoG[i]);}
			}
			
		}while(menu!=9);
		
		
	}
	public static void imprimeTime() {
		for(int i=0;i<contTimes;i++) {
			System.out.println(i+1+"."+teams[i].nome+" - "+teams[i].estado);}}
	
	public static void imprimeJogos(){
		for(int i=0;i<contJogos;i++) {
			
			System.out.println(i+1+"."+teams[games[i].timeMand-1].nome+" "+games[i].GolsMand+" X "+games[i].golsVisi+" "+teams[games[i].timeVisi-1].nome);}}

	public static void orgTabela() {
		int tempP,tempS;
		Times tempT = null;
		
		boolean swap = true;
		
		do {swap = false;
			for (int i = 0; i < contTimes-1; i++) {
				if (pontos[i] < pontos[i + 1]) {
					tempT = teams[i];	teams[i] = teams[i+1];teams[i+1] = tempT;
					tempP = pontos[i]; pontos[i] = pontos[i+1];pontos[i+1] = tempP;
					tempS = saldoG[i]; saldoG[i] = saldoG[i+1];saldoG[i+1] = tempS;
					swap=true;}
				}}while(swap == true);
		
			boolean swap2 = true;
			
		do {swap2 = false;
			for(int i = 0;i < contTimes-1;i++) {
				if(pontos[i] == pontos[i+1]) {
					if(saldoG[i] < saldoG[i+1]) {
						tempT = teams[i];	teams[i] = teams[i+1];teams[i+1] = tempT;
						tempP = pontos[i]; pontos[i] = pontos[i+1];pontos[i+1] = tempP;
						tempS = saldoG[i]; saldoG[i] = saldoG[i+1];saldoG[i+1] = tempS;
						swap2 = true;}}}
		}while(swap2 == true);
	}
	
}
