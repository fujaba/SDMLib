package de.uniks.jism.gui.brush;

/*
java-syntax-highlighter
Copyright (c) 2011 Chan Wai Shing

Permission is hereby granted, free of charge, to any person obtaining
a copy of this software and associated documentation files (the
"Software"), to deal in the Software without restriction, including
without limitation the rights to use, copy, modify, merge, publish,
distribute, sublicense, and/or sell copies of the Software, and to
permit persons to whom the Software is furnished to do so, subject to
the following conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

import java.util.regex.Pattern;
import org.sdmlib.serialization.Style;
/**
 * Bash brush.
 * @author Chan Wai Shing <cws1989@gmail.com>
 */

public class BrushBash extends Brush {
	public BrushBash() {
    super();

    String keywords = "if fi then elif else for do done until while break continue case function return in eq ne ge le";
    String commands = "alias apropos awk basename bash bc bg builtin bzip2 cal cat cd cfdisk chgrp chmod chown chroot"
            + "cksum clear cmp comm command cp cron crontab csplit cut date dc dd ddrescue declare df "
            + "diff diff3 dig dir dircolors dirname dirs du echo egrep eject enable env ethtool eval "
            + "exec exit expand export expr false fdformat fdisk fg fgrep file find fmt fold format "
            + "free fsck ftp gawk getopts grep groups gzip hash head history hostname id ifconfig "
            + "import install join kill less let ln local locate logname logout look lpc lpr lprint "
            + "lprintd lprintq lprm ls lsof make man mkdir mkfifo mkisofs mknod more mount mtools "
            + "mv netstat nice nl nohup nslookup open op passwd paste pathchk ping popd pr printcap "
            + "printenv printf ps pushd pwd quota quotacheck quotactl ram rcp read readonly renice "
            + "remsync rm rmdir rsync screen scp sdiff sed select seq set sftp shift shopt shutdown "
            + "sleep sort source split ssh strace su sudo sum symlink sync tail tar tee test time "
            + "times touch top traceroute trap tr true tsort tty type ulimit umask umount unalias "
            + "uname unexpand uniq units unset unshar useradd usermod users uuencode uudecode v vdir "
            + "vi watch wc whereis which who whoami Wget xargs yes";

    addRule(new RegExpRule("^#!.*$", Pattern.MULTILINE, "preprocessor", new Style().withBold(true)));
    addRule(new RegExpRule("\\/[\\w-\\/]+", Pattern.MULTILINE, "plain"));
    addRule(new RegExpRule(RegExpRule.singleLinePerlComments, "comments")); // one line comments
    addRule(new RegExpRule(RegExpRule.doubleQuotedString, "string")); // double quoted strings
    addRule(new RegExpRule(RegExpRule.singleQuotedString, "string")); // single quoted strings
    addRule(new RegExpRule(getKeywords(keywords), Pattern.MULTILINE, "keyword")); // keywords
    addRule(new RegExpRule(getKeywords(commands), Pattern.MULTILINE, "functions")); // commands

    setCommonFileExtensionList("sh");
  }
}
