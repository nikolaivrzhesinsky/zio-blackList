package dev.zio.quickstart.blackList

import java.util.UUID
import zio.json.*

case class InputPorts(src:String,dst:String,amount:Int)

object InputPorts:
  given JsonEncoder[InputPorts] =
    DeriveJsonEncoder.gen[InputPorts]
  given JsonDecoder[InputPorts] =
    DeriveJsonDecoder.gen[InputPorts]