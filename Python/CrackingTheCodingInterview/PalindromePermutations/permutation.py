from collections import defaultdict


def is_palindrome_permutation(word):
    already_found_an_odd = False
    for count in get_letter_count(word).values():
        if count % 2 == 0:
            continue

        if already_found_an_odd:
            return False
        already_found_an_odd = True

    return True


def get_letter_count(word):
    letter_map = defaultdict(int)

    for letter in word:
        if str.isalpha(letter):
            letter_map[letter.lower()] += 1

    return letter_map
