n = int(input()) 
mod = 1000000 

def matmul(mat1,mat2): 
    result = [[0,0],[0,0]] 
    for i in range(2): 
        for j in range(2): 
            for k in range(2): 
                result[i][j] = (result[i][j] + mat1[i][k] * mat2[k][j]) % mod 
    return result 

A0 = [[1], [0]]
mul_op = [[1, 1], [1, 0]] 
answer = [[1,0], [0, 1]] 

n = n - 1 

while(n > 0): 
    if (n % 2 == 1): 
        answer = matmul(answer, mul_op) 
    mul_op = matmul(mul_op, mul_op) 
    n = n // 2 
    
print(answer[0][0])