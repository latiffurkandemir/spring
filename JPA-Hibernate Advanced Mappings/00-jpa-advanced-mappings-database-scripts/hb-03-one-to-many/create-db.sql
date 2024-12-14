SELECT 
    tc.table_schema AS schema_name,
    tc.table_name AS table_name,
    kcu.column_name AS column_name,
    ccu.table_schema AS referenced_schema_name,
    ccu.table_name AS referenced_table_name,
    ccu.column_name AS referenced_column_name,
    rc.update_rule,
    rc.delete_rule
FROM 
    information_schema.table_constraints AS tc
JOIN 
    information_schema.key_column_usage AS kcu
    ON tc.constraint_name = kcu.constraint_name
    AND tc.table_schema = kcu.table_schema
JOIN 
    information_schema.constraint_column_usage AS ccu
    ON ccu.constraint_name = tc.constraint_name
    AND ccu.table_schema = tc.table_schema
JOIN 
    information_schema.referential_constraints AS rc
    ON tc.constraint_name = rc.constraint_name
    AND tc.table_schema = rc.constraint_schema
WHERE 
    tc.constraint_type = 'FOREIGN KEY'
    AND tc.table_name IN ('instructor', 'instructor_detail', 'course') -- Filter for these specific tables
ORDER BY 
    tc.table_schema, tc.table_name;
