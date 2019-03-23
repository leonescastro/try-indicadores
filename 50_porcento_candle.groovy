//ENTRADA DE DADOS//
int PERCENT_CANDLE = 50;

//FIM ENTRADA DE DADOS//

//r = newLines();

def low = low();
def high = high();
def size = low.size()

def linha50 = newLineData();
def maxima = high.value(size-1);
def minima = low.value(size-1);
def metadePreco = ((maxima-minima)*(PERCENT_CANDLE/100))+ minima;
      
for (int i=0; i<size; i++) {
      linha50.add(metadePreco.toDouble());
}

r=linha50;
