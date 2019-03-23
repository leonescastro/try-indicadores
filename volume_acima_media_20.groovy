//ENTRADA DE DADOS//
    //Periodo Media
    int MEDIA_PERIOD = 20;
    //Tipo Media
    //0 - Simples ( Aritmética )
    //1 - Exponencial 
    int MEDIA_TIPO = 0;
    
    //Cor RGB destaque volume amarelo
    int sR = 255;
    int sG = 255;
    int sB = 0;

    //Cor RGB volume cinza
    int cR = 153;
    int cG = 153;
    int cB = 153;

    //Cor RGB média azul marinho
    int mR = 0;
    int mG = 128;
    int mB = 255;

//FIM ENTRADA DE DADOS//

r = newLines();

def vol = sharesVolume();
def volume = newLineData();
def media = newLineData();
media = MA(vol, MEDIA_PERIOD, MEDIA_TIPO);

for (int i=0; i<vol.size(); i++) {
      // Variaveis de apoio
      def volAtu = vol.value(i);
      def mediaAtu = 0;

      if (i>(MEDIA_PERIOD-1)) {
            mediaAtu = media.value(i-(MEDIA_PERIOD-1));
      }

      // Volume maior que a média de 20
      if (i>(MEDIA_PERIOD-1) && volAtu >= mediaAtu) { 
            volume.add(volAtu , sR, sG, sB);
      } else { 
            volume.add(volAtu, cR, cG, cB); 
      }
}

volume.setLabel("VOL");
volume.setType(3);

media.setLabel("MA[" + MEDIA_PERIOD + "]");
media.setType(4);
media.setColor(mR, mG, mB);

r.add(volume);
r.add(media);
