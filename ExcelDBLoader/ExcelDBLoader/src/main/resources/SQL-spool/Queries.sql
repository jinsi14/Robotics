with
	product_categories
as (
SELECT
  oprbpo.intermediary_number as intermediary_number,
  oprbpo.product_code as product_code,
  pcm.FAIS_sub_categories_no as FAIS_sub_categories_no
FROM OMCD_PF_RBA_blue_prism_output oprbpo
LEFT JOIN product_category_mapping pcm ON oprbpo.product_code = pcm.product_code
where pcm.FAIS_sub_categories_no is NOT NULL
)
SELECT
	case
  when
    oarr.advisor_registered_on_FSCA_register = 1
    and oarr.advisor_appears_on_workday_rep_register = 'Yes'
    and oarr.product_category_found_on_FSCA_register = 'Yes'
    and oarr.product_category_found_on_workday_rep_register = 'Yes'
  then 'Authorised'
  when
    oarr.advisor_registered_on_FSCA_register = 0
	  or oarr.advisor_appears_on_workday_rep_register = 'No'
	  or oarr.product_category_found_on_FSCA_register = 'No'
	  or oarr.product_category_found_on_workday_rep_register = 'No'
  then 'Unauthorised'
	else NULL END as Authority,
	*
FROM product_categories pr
INNER JOIN OMCD_advisor_recon_report oarr ON oarr.sales_code = pr.intermediary_number AND oarr.product_category = pr.FAIS_sub_categories_no
