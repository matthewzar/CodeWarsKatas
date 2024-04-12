def max_sequence(nums):
    max_num, current = 0, 0
    for number in nums:
        current += number
        if current < 0:
            current = 0
        if current > max_num:
            max_num = current
    return max_num
