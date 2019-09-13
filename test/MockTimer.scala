import java.util.Timer
import java.util.TimerTask


class MockTimer(var tarefaAgendada:Boolean = false, var time:Long = 0) extends Timer{
  
  override def schedule(timerTask: TimerTask, time: Long) = {
    tarefaAgendada = true
    this.time = time
  }
}

