class Main {

    public static void main(String[] args) {

        int[][] tabuleiro = new int[8][8];
        int[] horizontal = {2, 1, -1, -2, -2, -1, 1, 2};
        int[] vertical = {-1, -2, -2, -1, 1, 2, 2, 1};
        
        int linhaInicial = 3, colunaInicial = 3;
        
        inicializaArray(tabuleiro);
        
        tabuleiro[linhaInicial][colunaInicial] = 1;
        
        System.out.printf("Movimento 1: (%d, %d)\n", linhaInicial, colunaInicial);
        exibeArray(tabuleiro);
        
        if (resolverPasseioDoCavalo(tabuleiro, linhaInicial, colunaInicial, 2, horizontal, vertical)) {
            System.out.println("O passeio do cavalo foi concluído com sucesso.");
        } else {
            System.out.println("O passeio do cavalo foi interrompido antes de completar todas as casas.");
        }
        
        exibeArray(tabuleiro);
    }

    final static void inicializaArray(int[][] tabuleiro) {
        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro[0].length; j++) {
                tabuleiro[i][j] = 0;
            }
        }
    }

    final static boolean resolverPasseioDoCavalo(int[][] tabuleiro, int linhaAtual, int colunaAtual, int movimento, int[] horizontal, int[] vertical) {
        int tamanho = tabuleiro.length;
    
        if (movimento == tamanho * tamanho + 1) {
            return true;
        }
    
        for (int k = 0; k < 8; k++) {
            int novaLinha = linhaAtual + horizontal[k];
            int novaColuna = colunaAtual + vertical[k];
    
            if (ehMovimentoValido(tabuleiro, novaLinha, novaColuna)) {
                tabuleiro[novaLinha][novaColuna] = movimento;
    
                //movimento atual
                System.out.printf("Movimento %d: (%d, %d)\n", movimento, novaLinha, novaColuna);
                exibeArray(tabuleiro);
    
                if (resolverPasseioDoCavalo(tabuleiro, novaLinha, novaColuna, movimento + 1, horizontal, vertical)) {
                    return true;
                } else {
                    System.out.printf("Nenhum movimento válido a partir de (%d, %d) para o movimento %d\n", novaLinha, novaColuna, movimento);
                }
                tabuleiro[novaLinha][novaColuna] = 0; // backtrack
    
                //tabuleiro após backtrack
                System.out.printf("Backtrack do movimento %d: (%d, %d)\n", movimento, novaLinha, novaColuna);
                exibeArray(tabuleiro);
            } else {
                System.out.printf("(%d, %d) não é um movimento válido para o movimento %d\n", novaLinha, novaColuna, movimento);
            }
        }
    
        return false;
    }



    final static boolean ehMovimentoValido(int[][] tabuleiro, int linha, int coluna) {
        return linha >= 0 && linha < tabuleiro.length && coluna >= 0 && coluna < tabuleiro[0].length && tabuleiro[linha][coluna] == 0;
    }

    final static void exibeArray(int[][] tabuleiro) {
        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro[0].length; j++) {
                if (tabuleiro[i][j] == 0) {
                    System.out.printf("|   ");
                } else {
                    System.out.printf("|%3d", tabuleiro[i][j]);
                }
            }
            System.out.println("|");
        }
        System.out.println();
    }
}