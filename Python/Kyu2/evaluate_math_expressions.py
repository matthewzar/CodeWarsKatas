def get_bracket_indexes(exp):
    start = -1
    for i in range(len(exp)):
        if exp[i] == ")":
            return start, i
        if exp[i] == "(":
            start = i
    return -1, -1

def get_last_op(operations, expression):
    for i in range(len(expression)-1,-1,-1):
        curr = expression[i]
        if curr not in operations:
            continue
            
        if curr == "-" and (i == 0 or expression[i-1] in "/*-+" ):
            continue
        
        return i
    
    return -1

def calc(expression):
    operations = {"+": lambda x,y: float(x)+float(y),
                 "-": lambda x,y: float(x)-float(y),
                  "*": lambda x,y: float(x)*float(y),
                 "/": lambda x,y: float(x)/float(y)}
    op_sets = [["+","-"], ["*","/"]]
    def solve_sub(exp):
        if "(" in exp:
            start, end = get_bracket_indexes(exp)
            inner = calc(exp[start+1:end])
            new_exp = exp[:start] + str(inner) + exp[end+1:]
            return solve_sub(new_exp)
        
        for op_set in op_sets:
            index = get_last_op(op_set, exp)
            if index > -1:
                op = exp[index]
                left, right = exp[:index], exp[index+1:]
                return operations[op](solve_sub(left), solve_sub(right))

        try:
            return float(exp)
        except:
            exp = exp.replace("--", "")
            return solve_sub(exp)    
    
    return solve_sub(expression.replace(" ", ""))