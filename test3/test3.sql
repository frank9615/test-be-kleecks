-- utilizzo coalesce per gestire il caso in cui non ci siano vendite per l'anno precedente
-- se non ci sono vendite allora la variazione è uguale all'anno in corso (totale) - 0

SELECT
    user,
    anno,
    SUM(totale) AS totale,
    SUM(totale) - coalesce(
        (SELECT
            SUM(totale)
        FROM
            test3.vendite v2
        WHERE
            v2.user = v1.user
            AND v2.anno = v1.anno - 1
        ),0) AS variazione
FROM
    test3.vendite v1
GROUP BY
    user,
    anno
ORDER BY
    user,
    anno;