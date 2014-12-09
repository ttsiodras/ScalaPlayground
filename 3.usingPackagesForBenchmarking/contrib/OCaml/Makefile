all:	compile

compile:	antML

antML:	ant.ml
	@echo "Compiling OCaml..."
	ocamlopt -unsafe -rectypes -inline 1000 -o $@ $<

bench:	antML
	@echo -e '\nBenchmarking OCaml...'
	@bash -c "for i in {1..10} ; do ( time -p ./antML ) 2>&1 ; done" | grep real | awk '{print $$2}' | stats.py

clean:
	rm -rf ant.cm? ant.o antML
