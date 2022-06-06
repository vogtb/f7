const ALPHA_NUMERIC_ALL_CASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
const ALPHA_UPPER_CASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

/**
 * Return a random 16 character string.
 * https://gist.github.com/jcxplorer/823878
 */
export function randomID() {
  return randomString(16);
}

/**
 * Generate a random UUID.
 * https://gist.github.com/jcxplorer/823878
 */
export function randomUUID() {
  let uuid = "",
    i,
    random;
  for (i = 0; i < 32; i++) {
    random = (Math.random() * 16) | 0;
    if (i == 8 || i == 12 || i == 16 || i == 20) {
      uuid += "-";
    }
    uuid += (i == 12 ? 4 : i == 16 ? (random & 3) | 8 : random).toString(16);
  }
  return uuid;
}

/**
 * Generate a random string of N length.
 * https://stackoverflow.com/questions/1349404/generate-random-string-characters-in-javascript
 * @param n - length of string returned
 */
export function randomString(n: number) {
  return randomFrom(n, ALPHA_NUMERIC_ALL_CASE);
}

/**
 * Generate a random string of length N.
 * @param n
 */
export function randomAlphaUppercase(n: number) {
  return randomFrom(n, ALPHA_UPPER_CASE);
}

function randomFrom(n: number, characters: string) {
  let text = "";

  for (let i = 0; i < n; i++)
    text += characters.charAt(Math.floor(Math.random() * characters.length));

  return text;
}

/**
 * Generate a hashcode for a given input string.
 * https://stackoverflow.com/questions/7616461/generate-a-hash-from-string-in-javascript
 *
 * @param input - the input to hash.
 * @param seed - seed to use.
 */
export function hashCode(input: string, seed = 0x811c9dc5) {
  let i,
    l,
    hval = seed;
  for (i = 0, l = input.length; i < l; i++) {
    hval ^= input.charCodeAt(i);
    hval += (hval << 1) + (hval << 4) + (hval << 7) + (hval << 8) + (hval << 24);
  }
  // Convert to 8 digit hex string
  return ("0000000" + (hval >>> 0).toString(16)).substr(-8);
}

/**
 * Generate a hashcode for a given object.
 *
 * @param input - the input to hash.
 * @param seed - seed to use.
 */
export function objectHashCode(input: any, seed?: number) {
  return hashCode(JSON.stringify(input), seed);
}

/**
 * Random int between 0 and 2,147,483,647.
 */
export function randomInt() {
  return Math.floor(Math.random() * 2147483647);
}
