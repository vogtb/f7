declare let navigator: any;

const userAgent = typeof navigator !== "undefined" ? navigator.userAgent : "";
const browser = {
  isEdge: /Edge/.test(userAgent),
  isInternetExplorer: /Trident|rv:11/.test(userAgent),
  isWebkit: /AppleWebKit/.test(userAgent),
};

export const Browser = {
  isEdge: () => browser.isEdge,
  isInternetExplorer: () => browser.isInternetExplorer,
  isWebkit: () => browser.isWebkit,
};
