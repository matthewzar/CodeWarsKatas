from Python.CrackingTheCodingInterview.PalindromePermutations.permutation import is_palindrome_permutation, get_letter_count
import pytest


class TestPalindromePermutation:
    def test_is_palindrome_permutation_true(self):
        assert is_palindrome_permutation("Tact Coa")  # "taco cat", "atco cta", etc.
        assert is_palindrome_permutation("a")
        assert is_palindrome_permutation("aa")
        assert is_palindrome_permutation("carerac")

    def test_is_palindrome_permutation_false(self):
        assert not is_palindrome_permutation("random")
        assert not is_palindrome_permutation("palindrome")

    def test_get_letter_count(self):
        assert get_letter_count("Tact Coa") == {'t': 2, 'a': 2, 'c': 2, 'o': 1}
        assert get_letter_count("a") == {'a': 1}
        assert get_letter_count("Aa") == {'a': 2}
        assert get_letter_count("Hello there!") == {'h': 2, 'e': 3, 'l': 2, 'o': 1, 't': 1, 'r': 1}

    def test_get_letter_count_non_alpha(self):
        assert get_letter_count("1234!!!???") == {}

    def test_empty_string(self):
        assert is_palindrome_permutation("")  # Empty string is a palindrome
        assert get_letter_count("") == {}

    def test_case_sensitivity(self):
        assert get_letter_count("AaBbCc") == {'a': 2, 'b': 2, 'c': 2}
        assert is_palindrome_permutation("AaBbBbAa")