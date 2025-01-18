select
    HR_EMPLOYEES.EMP_NO as EMP_NO,
    HR_EMPLOYEES.EMP_NAME as EMP_NAME,
    case 
        when AVG(HR_GRADE.SCORE) >= 96 then 'S'
        when AVG(HR_GRADE.SCORE) >= 90 then 'A'
        when AVG(HR_GRADE.SCORE) >= 80 then 'B'
        else 'C'
    end as GRADE,
    case
        when AVG(HR_GRADE.SCORE) >= 96 then round(HR_EMPLOYEES.SAL * 0.2, 0)
        when AVG(HR_GRADE.SCORE) >= 90 then round(HR_EMPLOYEES.SAL * 0.15, 0)
        when AVG(HR_GRADE.SCORE) >= 80 then round(HR_EMPLOYEES.SAL * 0.1, 0)
        else 0
    end as BONUS
from HR_EMPLOYEES
join HR_GRADE on HR_EMPLOYEES.EMP_NO = HR_GRADE.EMP_NO
group by HR_EMPLOYEES.EMP_NO
order by HR_EMPLOYEES.EMP_NO ASC;
