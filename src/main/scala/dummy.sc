val a = List("1","2","3")
a.toStream.map(_.toInt).sum