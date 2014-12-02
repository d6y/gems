package example2

object PaymentStatus {
  val NOT_STARTED = "n/a"
  val PENDING     = "pending"
  val COMPLETE    = "complete"
  val REJECTED    = "rejected"
}

object Problem extends App {

  def isFinished(status: String): Boolean =
    status == PaymentStatus.COMPLETE ||
    status == PaymentStatus.REJECTED

  // Run-time match error:
  "uh-oh" match {
    case PaymentStatus.NOT_STARTED | PaymentStatus.PENDING  => false
    case PaymentStatus.COMPLETE    | PaymentStatus.REJECTED => true
  }
}