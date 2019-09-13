import org.junit._
import org.junit.Assert._

class EncurtadorTest {
  var urlLonga = "http://www.teste_de_url_.com.br"
  val encurtador = new Encurtador(new MockTimer)
  val MENSAGEM_EXCECAO = "A URL sugerida é inválida!"
  
  @After
  def tearDown() = {
    encurtador.map.clear
  }
  
  @Test
  def testURLValida()={
    var t = 10
    val urlCurta = encurtador.encurta(urlLonga, timeout = t)
    assert(encurtador.recupera(urlCurta) == urlLonga)
    assertTrue("Não houve agendamento do timeout da URL encurtada", encurtador.timer.asInstanceOf[MockTimer].tarefaAgendada)
    assertEquals(t, encurtador.timer.asInstanceOf[MockTimer].time)
  }
  
  @Test
  def testURLComSugestaoDeURLCurtaValida()={
    val urlCurta = encurtador.encurta(urlLonga, "csgsxv")
    assertEquals(urlCurta, "http://m2b.com/csgsxv")
  }
  
  @Test
  def testURLComSugestaoDeURLCurtaInvalida() = {
    try{  
      encurtador.encurta(urlLonga, "`dsa")
      fail("Deveria ter lancado uma excecao")
    }catch {
      case e: IllegalArgumentException => {
        assertEquals(MENSAGEM_EXCECAO, e.getMessage)
      }
    }
  }
}