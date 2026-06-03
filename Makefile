
all: db-up package run

db-up:
	docker compose up -d

db-down:
	docker compose down

package:
	mvn clean package

run:
	@if [ -z "$(ARG)" ]; then \
		echo "Usage: make run ARG=< console or gui >"; \
		exit 1; \
	fi
	@java -jar target/Swingy-1.0.0.jar $(ARG)

clean: db-down
	mvn clean

re: clean all