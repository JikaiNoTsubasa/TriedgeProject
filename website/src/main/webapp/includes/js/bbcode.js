//https://blogs.stonesteps.ca/1/p/33

function insertBBCodeUnique(tag,elem) {
    var Field = document.getElementById(elem);
    var val = Field.value;
    var selected_txt = val.substring(Field.selectionStart, Field.selectionEnd);
    var before_txt = val.substring(0, Field.selectionStart);
    var after_txt = val.substring(Field.selectionEnd, val.length);
    Field.value = before_txt + '[' + tag + ']\n' + selected_txt + after_txt;
}
function insertBBCode(tag,elem) {
    var Field = document.getElementById(elem);
    var val = Field.value;
    var selected_txt = val.substring(Field.selectionStart, Field.selectionEnd);
    var before_txt = val.substring(0, Field.selectionStart);
    var after_txt = val.substring(Field.selectionEnd, val.length);
    Field.value = before_txt + '[' + tag + ']' + selected_txt + '[/' + tag + ']' + after_txt;
}
function insertBBCodeExtra(tag,tag2,elem) {
    var Field = document.getElementById(elem);
    var val = Field.value;
    var selected_txt = val.substring(Field.selectionStart, Field.selectionEnd);
    var before_txt = val.substring(0, Field.selectionStart);
    var after_txt = val.substring(Field.selectionEnd, val.length);
    Field.value = before_txt + '[' + tag + ']' + selected_txt + '[/' + tag2 + ']' + after_txt;
}

// -----------------------------------------------------------------------
// Copyright (c) 2008, Stone Steps Inc.
// All rights reserved
// http://www.stonesteps.ca/legal/bsd-license/
//
// This is a BBCode parser written in JavaScript. The parser is intended
// to demonstrate how to parse text containing BBCode tags in one pass
// using regular expressions.
//
// The parser may be used as a backend component in ASP or in the browser,
// after the text containing BBCode tags has been served to the client.
//
// Following BBCode expressions are recognized:
//
// [b]bold[/b]
// [i]italic[/i]
// [u]underlined[/u]
// [s]strike-through[/s]
// [samp]sample[/samp]
//
// [color=red]red[/color]
// [color=#FF0000]red[/color]
// [size=1.2]1.2em[/size]
//
// [url]http://blogs.stonesteps.ca/showpost.asp?pid=33[/url]
// [url=http://blogs.stonesteps.ca/showpost.asp?pid=33][b]BBCode[/b] Parser[/url]
//
// [q=http://blogs.stonesteps.ca/showpost.asp?pid=33]inline quote[/q]
// [q]inline quote[/q]
// [blockquote=http://blogs.stonesteps.ca/showpost.asp?pid=33]block quote[/blockquote]
// [blockquote]block quote[/blockquote]
//
// [pre]formatted
//     text[/pre]
// [code]if(a == b)
//   print("done");[/code]
//
// text containing [noparse] [brackets][/noparse]
//
// -----------------------------------------------------------------------
var opentags;           // open tag stack
var crlf2br = true;     // convert CRLF to <br>?
var noparse = false;    // ignore BBCode tags?
var urlstart = -1;      // beginning of the URL if zero or greater (ignored if -1)

// aceptable BBcode tags, optionally prefixed with a slash
var tagname_re = /^\/?(?:t1|t2|b|i|u|pre|samp|code|colou?r|size|noparse|url|s|q|blockquote|img|sep)$/;

// color names or hex color
var color_re = /^(:?black|silver|gray|white|maroon|red|purple|fuchsia|green|lime|olive|yellow|navy|blue|teal|aqua|#(?:[0-9a-f]{3})?[0-9a-f]{3})$/i;

// numbers
var number_re = /^[\\.0-9]{1,8}$/i;

// reserved, unreserved, escaped and alpha-numeric [RFC2396]
var uri_re = /^[-;\/\?:@&=\+\$,_\.!~\*'\(\)%0-9a-z]{1,512}$/i;

// main regular expression: CRLF, [tag=option], [tag] or [/tag]
var postfmt_re = /([\r\n])|(?:\[([a-z0-9]{1,16})(?:=([^\x00-\x1F"'\(\)<>\[\]]{1,256}))?\])|(?:\[\/([a-z0-9]{1,16})\])/ig;

// stack frame object
function taginfo_t(bbtag, etag)
{
    this.bbtag = bbtag;
    this.etag = etag;
}

// check if it's a valid BBCode tag
function isValidTag(str)
{
    if(!str || !str.length)
        return false;

    return tagname_re.test(str);
}

//
// m1 - CR or LF
// m2 - the tag of the [tag=option] expression
// m3 - the option of the [tag=option] expression
// m4 - the end tag of the [/tag] expression
//
function textToHtmlCB(mstr, m1, m2, m3, m4, offset, string)
{
    //
    // CR LF sequences
    //
    if(m1 && m1.length) {
        if(!crlf2br)
            return mstr;

        switch (m1) {
            case '\r':
                return "";
            case '\n':
                return "<br>";
        }
    }

    //
    // handle start tags
    //
    if(isValidTag(m2)) {
        console.log("TAG: "+m2);
        // if in the noparse state, just echo the tag
        if(noparse)
            return "[" + m2 + "]";

        // ignore any tags if there's an open option-less [url] tag
        if(opentags.length && opentags[opentags.length-1].bbtag == "url" && urlstart >= 0)
            return "[" + m2 + "]";

        switch (m2) {
            case "sep":
                return "<div class=\"tr-horizontal-splitter\"></div>";
            case "t1":
                opentags.push(new taginfo_t(m2, "</h1>"));
                return "<h1>";
            case "t2":
                opentags.push(new taginfo_t(m2, "</h2>"));
                return "<h2>";
            case "code":
                opentags.push(new taginfo_t(m2, "</div>"));
                crlf2br = false;
                return "<div class=\"tr-code\">";
                /*
                opentags.push(new taginfo_t(m2, "</code></pre>"));
                crlf2br = false;
                return "<pre><code>";

                 */

            case "pre":
                opentags.push(new taginfo_t(m2, "</pre>"));
                crlf2br = false;
                return "<pre>";

            case "img":
                if(!m3)
                    m3 = "";
                opentags.push(new taginfo_t(m2, "</img>"));
                return "<img src=\"" + m3 + "\">";

            case "color":
            case "colour":
                if(!m3 || !color_re.test(m3))
                    m3 = "inherit";
                opentags.push(new taginfo_t(m2, "</span>"));
                return "<span style=\"color: " + m3 + "\">";

            case "size":
                if(!m3 || !number_re.test(m3))
                    m3 = "1";
                opentags.push(new taginfo_t(m2, "</span>"));
                return "<span style=\"font-size: " + Math.min(Math.max(m3, 0.7), 3) + "em\">";

            case "s":
                opentags.push(new taginfo_t(m2, "</span>"));
                return "<span style=\"text-decoration: line-through\">";

            case "noparse":
                noparse = true;
                return "";

            case "url":
                opentags.push(new taginfo_t(m2, "</a>"));

                // check if there's a valid option
                if(m3 && uri_re.test(m3)) {
                    // if there is, output a complete start anchor tag
                    urlstart = -1;
                    return "<a href=\"" + m3 + "\">";
                }

                // otherwise, remember the URL offset
                urlstart = mstr.length + offset;

                // and treat the text following [url] as a URL
                return "<a href=\"";

            case "q":
            case "blockquote":
                opentags.push(new taginfo_t(m2, "</" + m2 + ">"));
                return m3 && m3.length && uri_re.test(m3) ? "<" + m2 + " cite=\"" + m3 + "\">" : "<" + m2 + ">";

            default:
                // [samp], [b], [i] and [u] don't need special processing
                opentags.push(new taginfo_t(m2, "</" + m2 + ">"));
                return "<" + m2 + ">";

        }
    }

    //
    // process end tags
    //
    if(isValidTag(m4)) {
        if(noparse) {
            // if it's the closing noparse tag, flip the noparse state
            if(m4 == "noparse")  {
                noparse = false;
                return "";
            }

            // otherwise just output the original text
            return "[/" + m4 + "]";
        }

        // highlight mismatched end tags
        if(!opentags.length || opentags[opentags.length-1].bbtag != m4)
            return "<span style=\"color: red\">[/" + m4 + "]</span>";

        if(m4 == "url") {
            // if there was no option, use the content of the [url] tag
            if(urlstart > 0)
                return "\">" + string.substr(urlstart, offset-urlstart) + opentags.pop().etag;

            // otherwise just close the tag
            return opentags.pop().etag;
        }
        else if(m4 == "code" || m4 == "pre")
            crlf2br = true;

        // other tags require no special processing, just output the end tag
        return opentags.pop().etag;
    }

    return mstr;
}

//
// post must be HTML-encoded
//
function parseBBCode(post)
{
    var result, endtags, tag;

    // convert CRLF to <br> by default
    crlf2br = true;

    // create a new array for open tags
    if(opentags == null || opentags.length)
        opentags = new Array(0);

    // run the text through main regular expression matcher
    result = post.replace(postfmt_re, textToHtmlCB);

    // reset noparse, if it was unbalanced
    if(noparse)
        noparse = false;

    // if there are any unbalanced tags, make sure to close them
    if(opentags.length) {
        endtags = new String();

        // if there's an open [url] at the top, close it
        if(opentags[opentags.length-1].bbtag == "url") {
            opentags.pop();
            endtags += "\">" + post.substr(urlstart, post.length-urlstart) + "</a>";
        }

        // close remaining open tags
        while(opentags.length)
            endtags += opentags.pop().etag;
    }

    return endtags ? result + endtags : result;
}