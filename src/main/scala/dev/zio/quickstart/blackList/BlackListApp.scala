package dev.zio.quickstart.blackList

import zhttp.http.*
import zio.*
import zio.json.*

object BlackListApp :

  def apply(): Http[Any, Throwable, Request, Response] =
    Http.collectZIO[Request] {
      // POST /users -d '{"name": "John", "age": 35}'
      case req@(Method.POST -> !! / "transaction-check") =>
        for
          u <- req.bodyAsString.map(_.fromJson[InputPorts])
          r <- ZIO.fromEither(u)
            case Left(e) =>
              ZIO.debug(s"Failed to parse the input: $e").as(
                Response.text(e).setStatus(Status.BadRequest)
              )
            case Right(u) =>
              Response.text("Good request!")
        yield r
    }





