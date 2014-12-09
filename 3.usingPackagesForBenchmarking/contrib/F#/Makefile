all:	compile

compile:	ant.exe

ant.exe:	ant.fs
	@echo "Compiling F#..."
	@fsharpc --checked- --optimize+ $<

bench:	ant.exe
	@echo -e '\nBenchmarking F#...'
	@./ant.exe | grep -v Points | stats.py

clean:
	rm -rf ant.exe
