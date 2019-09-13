
import scala.collection.mutable.Map
import java.util.Timer
import java.util.TimerTask


class Encurtador(val timer: Timer = new Timer) {
  
	val prefixo = "http://m2b.com/"
	var map:Map[String, String] = Map.empty
	val ERRO_EXCECAO = "A URL sugerida é inválida!"
	
	
  def encurta(urlOriginal: String, urlCurta: String = "", timeout: Int = 1000):String = {
           
  	val random = scala.util.Random                                   
  	val charInvalidos = "`\'\"!@#$%^&*(){}[]\\|/~?.,+<>"
                       
  	if(urlCurta != ""){
  	  for(c <- urlCurta) 
  	    if(charInvalidos.contains(c)) 
  	      throw new IllegalArgumentException(ERRO_EXCECAO)
  	}else{
    	for(i <- 1 to 5){
    		var proxChar = random.nextPrintableChar
    		while (charInvalidos.contains(proxChar)){
    		  proxChar = random.nextPrintableChar
    		}
    		urlCurta.concat(proxChar.toString())
    	}
  	}
  	var urlCurtaFinal = prefixo.concat(urlCurta)
  	map += (urlCurtaFinal -> urlOriginal)
  	timer.schedule(
  	    new TimerTask{def run() = map - urlCurtaFinal}, 
  	    timeout)
  	
  	urlCurtaFinal
  }
  
  def recupera(urlCurta:String):String = {
    map(urlCurta)
  }  
}
