import pandas as pd
import matplotlib.pyplot as plt
import os

df = pd.read_csv("resultados.csv", sep=";")

df["tempo_ms"] = (
    df["tempo_ms"]
    .astype(str)
    .str.replace(",", ".", regex=False)
    .astype(float)
)

arquivos = df["arquivo"].unique()

os.makedirs("boxplots", exist_ok=True)

for arquivo in arquivos:
    df_arq = df[df["arquivo"] == arquivo]
    
    # lista
    df_lista = df_arq[df_arq["estrutura"] == "LISTA"]
    if not df_lista.empty:
        plt.figure(figsize=(10, 6))
        df_lista.boxplot(column="tempo_ms", by="algoritmo")
        plt.title(f"Boxplot — LISTA — {arquivo}")
        plt.suptitle("")
        plt.ylabel("Tempo (ms)")
        plt.xticks(rotation=45)
        plt.tight_layout()
        plt.savefig(f"boxplots/{arquivo}_LISTA.png", dpi=300)
        plt.close()

    # matriz
    df_matriz = df_arq[df_arq["estrutura"] == "MATRIZ"]
    if not df_matriz.empty:
        plt.figure(figsize=(10, 6))
        df_matriz.boxplot(column="tempo_ms", by="algoritmo")
        plt.title(f"Boxplot — MATRIZ — {arquivo}")
        plt.suptitle("")
        plt.ylabel("Tempo (ms)")
        plt.xticks(rotation=45)
        plt.tight_layout()
        plt.savefig(f"boxplots/{arquivo}_MATRIZ.png", dpi=300)
        plt.close()

print("Boxplots gerados em: boxplots/")
