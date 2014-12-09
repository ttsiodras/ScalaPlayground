TARGET:=target/pack/bin/scala-benchmark
SRC:=src/main/scala/main.scala src/main/scala/common.scala src/main/scala/engines/functional.scala

all:	compile bench

compile:
	$(MAKE) -C "contrib/OCaml/"
	$(MAKE) -C "contrib/F#/"
	$(MAKE) ${TARGET}

bench:
	$(MAKE) -C "contrib/OCaml/" bench
	$(MAKE) -C "contrib/F#/" bench
	$(MAKE) benchScala

${TARGET}:	${SRC}
	sbt compile
	sbt pack

benchScala:	${TARGET}
	@echo -e '\nBenchmarking Scala...\n'
	$<

clean:
	$(MAKE) -C "contrib/OCaml/" clean
	$(MAKE) -C "contrib/F#/" clean
	rm -rf target project/project/ project/target/
