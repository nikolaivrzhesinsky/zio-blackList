package dev.zio.quickstart.blackList

import zhttp.http.*
import zio.*
import zio.json.*

import scala.language.postfixOps
import scala.util.Try
import scala.util.control.Breaks.break

object BlackListApp :

  def apply(): Http[Any, Throwable, Request, Response] =
    Http.collectZIO[Request] {

      case req@(Method.POST -> !! / "transaction-check") =>
        for
          u <- req.bodyAsString.map(_.fromJson[InputPorts])
          r <- u.match
            case Left(e) =>
              ZIO.debug(s"Failed to parse the input: $e").as(
                Response.text(e).setStatus(Status.BadRequest)
              )
            case Right(u) => {

              val fileName = "C:\\Users\\15nk3\\Documents\\GitHub\\zio-quickstart-restful-webservice-master\\src\\main\\resources\\blackList.txt"
              val res: String = "Success"
              val bufferedSource = scala.io.Source.fromFile(fileName)

              for (lines <- bufferedSource.getLines()) {
                // do something with lines
                if(u.src== lines || u.dst==lines ){

                  solution = "Cancel"
                  break

                }
                println(lines);
              }
              bufferedSource.close()



              ZIO.succeed(Response.text(res));


            }


        yield r

    }





