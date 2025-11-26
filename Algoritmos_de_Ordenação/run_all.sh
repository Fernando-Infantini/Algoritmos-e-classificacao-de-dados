#!/bin/bash

# ==============================================================================
# CONFIGURAÇÃO DE ALGORITMOS
# ==============================================================================

# Lista de TODOS os algoritmos que você tem
ALL_ALGORITHMS="Bubble Selection Insertion Merge Quick Heap Radix Shell Bucket Counting"

# Algoritmos O(N^2) que serão lentos em N=1.000.000.
# Serão excluídos da primeira rodada de benchmark6.sh
SLOW_ALGORITHMS="Bubble Selection Insertion"

# Variável para armazenar os algoritmos rápidos/normais
FAST_ALGORITHMS=""

# Número de repetições
NUM_REPETITIONS=10

# --- 1. Determina os Algoritmos Rápidos/Normais ---
for alg in $ALL_ALGORITHMS; do
    IS_SLOW=0
    for slow_alg in $SLOW_ALGORITHMS; do
        if [ "$alg" == "$slow_alg" ]; then
            IS_SLOW=1
            break
        fi
    done
    if [ $IS_SLOW -eq 0 ]; then
        FAST_ALGORITHMS="$FAST_ALGORITHMS $alg"
    fi
done

# Remove espaços em branco extras
FAST_ALGORITHMS=$(echo $FAST_ALGORITHMS | xargs)
SLOW_ALGORITHMS=$(echo $SLOW_ALGORITHMS | xargs)


echo "================================================================="
echo "INICIANDO BENCHMARK DE ORDENAÇÃO (x$NUM_REPETITIONS)"
echo "-----------------------------------------------------------------"
echo "Todos Algoritmos: $ALL_ALGORITHMS"
echo "Algoritmos Rápidos (N=1M): $FAST_ALGORITHMS"
echo "Algoritmos Lentos (N=1M): $SLOW_ALGORITHMS"
echo "================================================================="


# ==============================================================================
# ETAPA 1: Testes de N=100 a N=100.000 (TODOS os Algoritmos)
# Esses testes são rápidos e rodam em paralelo
# ==============================================================================
echo -e "\n--- ETAPA 1: N=100 a N=100000 (x$NUM_REPETITIONS) ---\n"
for i in $(seq $NUM_REPETITIONS); do
    echo "Rodada $i/$NUM_REPETITIONS: Executando benchmark.sh para TODOS ($ALL_ALGORITHMS)"
    ./benchmark.sh $ALL_ALGORITHMS
    echo "Rodada $i/$NUM_REPETITIONS: Consolidando logs com put_together.sh"
    ./put_together.sh $ALL_ALGORITHMS
done
echo -e "\n--- ETAPA 1 FINALIZADA ---\n"


# ==============================================================================
# ETAPA 2: Testes de N=1.000.000 (Algoritmos RÁPIDOS)
# ==============================================================================
echo -e "\n--- ETAPA 2: N=1.000.000 (x$NUM_REPETITIONS) - RÁPIDOS ---\n"
for i in $(seq $NUM_REPETITIONS); do
    echo "Rodada $i/$NUM_REPETITIONS: Executando benchmark6.sh para RÁPIDOS ($FAST_ALGORITHMS)"
    ./benchmark6.sh $FAST_ALGORITHMS
done
echo -e "\n--- ETAPA 2 FINALIZADA ---\n"


# ==============================================================================
# ETAPA 3: Testes de N=1.000.000 (Algoritmos LENTOS)
# Essa é a última etapa, garantindo que os lentos comecem por último
# ==============================================================================
echo -e "\n--- ETAPA 3: N=1.000.000 (x$NUM_REPETITIONS) - LENTOS ---\n"
if [ ! -z "$SLOW_ALGORITHMS" ]; then
    for i in $(seq $NUM_REPETITIONS); do
        echo "Rodada $i/$NUM_REPETITIONS: Executando benchmark6.sh para LENTOS ($SLOW_ALGORITHMS) - ATENÇÃO: pode demorar MUITO"
        ./benchmark6.sh $SLOW_ALGORITHMS
    done
fi
echo -e "\n--- ETAPA 3 FINALIZADA ---\n"


echo -e "\n================================================================="
echo "TODOS OS BENCHMARKS CONCLUÍDOS!"
echo "Resultados estão em ./results/"
echo "================================================================="